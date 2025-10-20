package es.equiperocket.sci;

import es.equiperocket.sci.controller.CursoController;
import es.equiperocket.sci.model.Coordenador;
import es.equiperocket.sci.model.Curso;
import es.equiperocket.sci.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        this.mockMvc = MockMvcBuilders.standaloneSetup(cursoController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void cursoTest() throws Exception {
        Coordenador coordenadorMock = new Coordenador();
        coordenadorMock.setNome("Coord Mock");

        Curso curso1 = new Curso("Engenharia", 1.1, coordenadorMock);
        curso1.setCodigo(1L);
        List<Curso> cursos = List.of(curso1);

        when(cursoService.listarCursos()).thenReturn(cursos);

        mockMvc.perform(get("/cursos"))
                .andExpect(status().isOk())
                .andExpect(view().name("cursos"))
                .andExpect(model().attributeExists("cursos"))
                .andExpect(model().attribute("cursos", hasSize(1)));
    }
}
