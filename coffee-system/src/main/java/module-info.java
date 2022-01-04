module jesperancinha.system {
    exports com.jesperancinha.coffee.system.manager to spring.beans, jesperancinha.api, lombok;
    exports com.jesperancinha.coffee.system.launcher to spring.beans;
    exports com.jesperancinha.coffee.system.queues to spring.beans;
    opens com.jesperancinha.coffee.system.manager;
    opens com.jesperancinha.coffee.system.launcher to args4j;
    requires spring.beans;
    requires spring.context;
    requires jesperancinha.api;
    requires lombok;
    requires org.slf4j;
    requires java.xml;
    requires jakarta.xml.bind;
    requires jakarta.activation;
    requires args4j;
    requires org.apache.logging.slf4j;
}