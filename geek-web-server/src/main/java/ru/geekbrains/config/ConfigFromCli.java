package ru.geekbrains.config;

class ConfigFromCli implements ServerConfig {

    private final String www;

    private final int port;

    public ConfigFromCli(String[] args) {
        System.out.println("Getting config from command line parameters");
        if (args.length < 2) {
            throw new IllegalStateException("Not enough parameter specified");
        }
        this.www = args[0];
        this.port = Integer.parseInt(args[1]);
    }

    @Override
    public String getWww() {
        return this.www;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
