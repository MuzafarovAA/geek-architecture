package ru.geekbrains.config;

class ConfigFromFixedValues implements ServerConfig {

    @Override
    public String getWww() {
        return "/Users/aleks/dev/geek-architecture-02/www";
    }

    @Override
    public int getPort() {
        return 8088;
    }
}
