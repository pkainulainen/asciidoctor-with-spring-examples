package net.petrikainulainen.spring.asciidoctor.controller;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Petri Kainulainen
 */
public class AsciidoctorControllerTest {

    private MockMvc mockMvc;

    private WebClient webClient;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AsciidoctorController()).build();

        webClient = new WebClient();
        webClient.setWebConnection(new MockMvcWebConnection(mockMvc, ""));
    }

    @Test
    public void renderDocumentListPage_ShouldReturnHttpResponseStatusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void renderDocumentListPage_ShouldRenderDocumentListPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("documentList"));
    }

    @Test
    public void renderAsciidocDocument_ShouldReturnHttpResponseStatusOk() throws Exception {
        mockMvc.perform(get("/asciidoctor/document"))
                .andExpect(status().isOk());
    }

    @Test
    public void renderAsciidocDocument_ShouldSetContentTypeToTextHtml() throws Exception {
        mockMvc.perform(get("/asciidoctor/document"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Test
    public void renderAsciidocDocument_ShouldRenderAsciidocDocumentAsHtml() throws IOException {
        HtmlPage asciidocDocumentPage = webClient.getPage("http://localhost/asciidoctor/document");
        assertThat(asciidocDocumentPage.getTitleText()).isEqualTo("Hello, AsciiDoc (File)!");
    }

    @Test
    public void renderAsciidocString_ShouldReturnHttpResponseStatusOk() throws Exception {
        mockMvc.perform(get("/asciidoctor/string"))
                .andExpect(status().isOk());
    }

    @Test
    public void renderAsciidocString_ShouldSetContentTypeToTextHtml() throws Exception {
        mockMvc.perform(get("/asciidoctor/string"))
                .andExpect(content().contentType(MediaType.TEXT_HTML));
    }

    @Test
    public void renderAsciidocString_ShouldRenderAsciidocStringAsHtml() throws IOException {
        HtmlPage asciidocStringPage = webClient.getPage("http://localhost/asciidoctor/string");
        assertThat(asciidocStringPage.getTitleText()).isEqualTo("Hello, AsciiDoc (String)!");
    }
}
