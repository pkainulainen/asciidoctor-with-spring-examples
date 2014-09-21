package net.petrikainulainen.spring.asciidoctor.controller;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

/**
 * This base class can render any Asciidoctor document as long as its subclasses
 * set the content type of the expected response by using the
 * {@link org.springframework.web.servlet.view.AbstractView#setContentType(String)} method,
 * provide the configuration that is used to configure Asciidoctor, and a {@link java.io.Reader}
 * object that is used to read the content of the rendered Asciidoc document.
 *
 * @author Petri Kainulainen
 */
public abstract class AbstractAsciidoctorHtmlView extends AbstractView {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAsciidoctorHtmlView.class);

    public AbstractAsciidoctorHtmlView() {
        super.setContentType(MediaType.TEXT_HTML_VALUE);
    }

    /**
     * Returns a {@link java.io.Reader} object that is used to read the content
     * of the rendered Asciidoctor document.
     * @return
     */
    protected abstract Reader getAsciidocMarkupReader();

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        LOGGER.info("Rendering asciidoctor document");

        response.setContentType(super.getContentType());

        Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Options asciidoctorOptions = getAsciidoctorOptions();

        try (
                Reader asciidoctorMarkupReader = getAsciidocMarkupReader();
                Writer responseWriter = response.getWriter();

        ) {
            asciidoctor.render(asciidoctorMarkupReader, responseWriter, asciidoctorOptions);
        }

        LOGGER.info("Rendered asciidoctor view.");
    }

    private Options getAsciidoctorOptions() {
        Options asciiDoctorOptions = new Options();
        asciiDoctorOptions.setHeaderFooter(true);
        return asciiDoctorOptions;
    }
}
