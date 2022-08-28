package bridge;

public class Careers implements WebPage {

    private final Theme theme;

    public Careers(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Careers page in " + theme.getColor();
    }
}
