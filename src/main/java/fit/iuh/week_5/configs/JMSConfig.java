package fit.iuh.week_5.configs;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JMSConfig {
  private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
  private static final String BROKER_USERNAME = "admin";
  private static final String BROKER_PASSWORD = "admin";

  @Bean
  public ConnectionFactory connectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
    connectionFactory.setUserName(BROKER_USERNAME);
    connectionFactory.setPassword(BROKER_PASSWORD);
    return connectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setDefaultDestinationName("order");
    template.setDeliveryPersistent(false);
    return template;
  }
}