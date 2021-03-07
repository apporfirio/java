package config;

public enum TargetBrowserName {

    CHROME("CHROME"),
    FIREFOX("FIREFOX");

    private final String text;

    TargetBrowserName(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
