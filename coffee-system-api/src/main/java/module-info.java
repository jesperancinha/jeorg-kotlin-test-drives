module jesperancinha.api {
    requires java.xml;
    requires java.xml.bind;
    opens com.jesperancinha.coffee.system.input to java.xml.bind;
    exports com.jesperancinha.coffee.api.concurrency;
    exports com.jesperancinha.coffee.system.input;
    exports com.jesperancinha.coffee.api.manager;
    exports com.jesperancinha.coffee.api.utils;
    exports com.jesperancinha.coffee.api.queues;
    exports com.jesperancinha.coffee.api.stats;
}