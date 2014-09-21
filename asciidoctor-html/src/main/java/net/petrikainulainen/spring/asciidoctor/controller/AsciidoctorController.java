package net.petrikainulainen.spring.asciidoctor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Petri Kainulainen
 */
@Controller
public class AsciidoctorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsciidoctorController.class);

    private static final String ASCIIDOC_FILE_LOCATION = "/asciidoctor/document.adoc";

    private static final String ASCIIDOC_STRING = "= Hello, AsciiDoc (String)!\n" +
            "Doc Writer <doc@example.com>\n" +
            "\n" +
            "An introduction to http://asciidoc.org[AsciiDoc].\n" +
            "\n" +
            "== First Section\n" +
            "\n" +
            "* item 1\n" +
            "* item 2\n" +
            "\n" +
            "[source,ruby]\n" +
            "puts \"Hello, World!\"";

    private static final String VIEW_NAME_DOCUMENT_LIST = "documentList";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderDocumentListPage() {
        LOGGER.info("Rendering document list page");
        return VIEW_NAME_DOCUMENT_LIST;
    }

    @RequestMapping(value = "/asciidoctor/document", method = RequestMethod.GET)
    public ModelAndView renderAsciidocDocument() {
        LOGGER.info("Rendering Asciidoc document");
        return new ModelAndView(new ClasspathFileAsciidoctorHtmlView(ASCIIDOC_FILE_LOCATION));
    }

    @RequestMapping(value = "/asciidoctor/string", method = RequestMethod.GET)
    public ModelAndView renderAsciidocString() {
        LOGGER.info("Rendering Asciidoc string");
        return new ModelAndView(new StringAsciidoctorHtmlView(ASCIIDOC_STRING));
    }

}
