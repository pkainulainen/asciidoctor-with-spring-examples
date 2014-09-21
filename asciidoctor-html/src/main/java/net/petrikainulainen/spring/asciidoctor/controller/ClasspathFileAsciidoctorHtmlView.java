package net.petrikainulainen.spring.asciidoctor.controller;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This view class can render an Asciidoctor document found from the classpath
 * as HTML.
 *
 * @author Petri Kainulainen
 */
public class ClasspathFileAsciidoctorHtmlView extends AbstractAsciidoctorHtmlView {

    private final String asciidocFileLocation;

    /**
     * Creates a new view object and sets the location of the rendered Asciidoctor
     * document.
     *
     * @param asciidocFileLocation The location of the rendered Asciidoctor document.
     *
     * @see {@link java.lang.Class#getResourceAsStream(String)} for more detail about the syntax that should be used
     * when you configure the location of the rendered Asciidoctor document.
     */
    public ClasspathFileAsciidoctorHtmlView(String asciidocFileLocation) {
        super();
        this.asciidocFileLocation = asciidocFileLocation;
    }

    @Override
    protected Reader getAsciidocMarkupReader() {
        return new InputStreamReader(this.getClass().getResourceAsStream(asciidocFileLocation));
    }
}
