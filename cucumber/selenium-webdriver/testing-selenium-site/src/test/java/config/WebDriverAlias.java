package config;

public enum WebDriverAlias {

    CHROME("CHROME"),
    FIREFOX("FIREFOX");

    private final String text;

    WebDriverAlias(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
