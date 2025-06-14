package top.gx.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author Lenovo
 */
@Data
@Slf4j
@Configuration
@IntegrationComponentScan
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private String brokerUrl;
    private String username;
    private String password;
    private String clientId;
    private String defaultTopic;

    @PostConstruct
    public void init() {
        log.info("MQTT 主机: {} 客户端 ID: {} 默认主题： {}", this.brokerUrl, this.clientId, this.defaultTopic);
    }

    /**
     *
     * 配置并返回⼀个
     * MqttPahoClientFactory
     * 实例，⽤于创建
     * MQTT
     * 客户端连接。
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        // 设置连接选项，包括服务器URI、⽤户名和密码。
        final MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { brokerUrl });
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        // 添加自动重连配置
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(30);
        options.setKeepAliveInterval(60);
        options.setCleanSession(true);

        final DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }

    // 出站通道（发送消息）
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + "_out", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("iot/device/control");
        // 添加连接状态监听
        messageHandler.setCompletionTimeout(5000);
        messageHandler.setDefaultQos(0);
        return messageHandler;
    }

    // ⼊站通道（接收消息）
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                clientId + "_in", mqttClientFactory(), "iot/device/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());
        // 添加连接状态监听
        adapter.setAutoStartup(true);
        return adapter;
    }

}
