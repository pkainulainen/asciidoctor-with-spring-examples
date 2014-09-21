package net.petrikainulainen.spring.asciidoctor.controller;

import java.io.Reader;
import java.io.StringReader;

/**
 * This view class can render an Asciidoctor document found from a String object
 * as HMTL.
 *
 * @author Petri Kainulainen
 */
public class StringAsciidoctorHtmlView extends AbstractAsciidoctorHtmlView {

    private final String asciidocMarkup;

    /**
     * Creates a new view object and sets the rendered Asciidoctor markup.
     *
     * @param asciidocMarkup The rendered Asciidoctor markup.
     */
    public StringAsciidoctorHtmlView(String asciidocMarkup) {
        super();
        this.asciidocMarkup = asciidocMarkup;
    }

    @Override
    protected Reader getAsciidocMarkupReader() {
        return new StringReader(asciidocMarkup);
    }
}
