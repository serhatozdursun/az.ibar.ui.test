package mapping;

public enum MapValue {
    ID("id"),
    CLASSNAME("className"),
    LINK_TEXT("linkText"),
    CSS_SELECTOR("cssSelector"),
    XPATH("xpath"),
    ID_CONTAINS("contains");

    private final String text;

    public String getText() {
        return text;
    }

    MapValue(String text) {
        this.text = text;
    }
}
