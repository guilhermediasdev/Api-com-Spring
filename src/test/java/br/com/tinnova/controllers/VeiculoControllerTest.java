package br.com.tinnova.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tinnova.models.Veiculo;
import br.com.tinnova.models.dto.VeiculoRequestDto;
import br.com.tinnova.services.VeiculoService;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {
	
	 	@Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ObjectMapper mapper;

	    @MockBean
	    private VeiculoService veiculoService;

	    Veiculo RECORD_1 = new Veiculo(110l,"TesteCar1", "MarcarTest1", 2018, "DSCAR1", Boolean.FALSE, LocalDate.now(), null);
	    Veiculo RECORD_2 = new Veiculo(210l,"TesteCar2", "MarcarTest2", 2019, "DSCAR2", Boolean.FALSE, LocalDate.now(), null);
	    Veiculo RECORD_3 = new Veiculo(310l,"TesteCar3", "MarcarTest3", 2020, "DSCAR2", Boolean.FALSE, LocalDate.now(), null);

	    @Test
	    public void getAllVeiculos_success() throws Exception {
	        List<Veiculo> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

	        Mockito.when(veiculoService.listarTodosVeiculo()).thenReturn(records);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/veiculos")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$", hasSize(3)))
	                .andExpect(jsonPath("$[2].nome", is("TesteCar3")));
	    }


	    @Test
	    public void getVeiculoId_success() throws Exception {
	        List<Veiculo> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

	        Mockito.when(veiculoService.consultarVeiculoPeloID(210L)).thenReturn(records.get(1));

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/veiculos/210")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.id", is(210)));
	    }

	    
	    @Test
	    public void getVeiculosMarca_success() throws Exception {
	        List<Veiculo> records = new ArrayList<>(Arrays.asList(RECORD_1));

	        Mockito.when(veiculoService.consultarVeiculosPelaMarca("MarcarTest1")).thenReturn(records);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/veiculos/filter")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
	    
	    
	    @Test
	    public void getVeiculosAno_success() throws Exception {
	        List<Veiculo> records = new ArrayList<>(Arrays.asList(RECORD_1));

	        Mockito.when(veiculoService.consultarVeiculosPeloAno(2018)).thenReturn(records);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/veiculos/filter")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }
	    
	    @Test
	    public void getVeiculosVendido_success() throws Exception {
	        List<Veiculo> records = new ArrayList<>(Arrays.asList(RECORD_1));

	        Mockito.when(veiculoService.consultarVeiculosPeloVendido(Boolean.FALSE)).thenReturn(records);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .get("/veiculos/filter")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

	    
	    @Test
	    public void createVeiculo_success() throws Exception {
	        VeiculoRequestDto veiculo = VeiculoRequestDto.builder()
	                .id(120L)
	                .ano(2021)
	                .nome("CarCreateTest")
	                .marca("Test")
	                .descricao("Teste de criacao")
	                .vendido(Boolean.FALSE)
	                .build();
	        
	        Veiculo created = Veiculo.builder()
	        		.id(veiculo.getId())
	        		.ano(veiculo.getAno())
	        		.nome(veiculo.getNome())
	        		.marca(veiculo.getMarca())
	        		.descricao(veiculo.getDescricao())
	        		.vendido(veiculo.getVendido())
	        		.created(LocalDate.now())
	        		.build();

	        Mockito.when(veiculoService.cadastrar(veiculo)).thenReturn(created);

	        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/veiculos")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(veiculo));

	        mockMvc.perform(mockRequest)
	                .andExpect(status().isCreated());
	    }

	    @Test
	    public void updateVeiculoRecord_success() throws Exception {
	    	VeiculoRequestDto veiculoUpdate = VeiculoRequestDto.builder()
	                .id(110L)
	                .ano(2021)
	                .nome("CarUpdateTest")
	                .marca("Test")
	                .descricao("Teste de atualizao")
	                .vendido(Boolean.TRUE)
	                .build();
	    	
	        Veiculo updated = Veiculo.builder()
	        		.id(veiculoUpdate.getId())
	        		.ano(veiculoUpdate.getAno())
	        		.nome(veiculoUpdate.getNome())
	        		.marca(veiculoUpdate.getMarca())
	        		.descricao(veiculoUpdate.getDescricao())
	        		.vendido(veiculoUpdate.getVendido())
	        		.updated(LocalDate.now())
	        		.build();

	        Mockito.when(veiculoService.consultarVeiculoPeloID(veiculoUpdate.getId())).thenReturn(RECORD_1);
	        Mockito.when(veiculoService.atualizar(veiculoUpdate)).thenReturn(updated);

	        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/veiculos")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(veiculoUpdate));

	        mockMvc.perform(mockRequest)
	                .andExpect(status().isNotFound());
	    }

	    @Test
	    public void deleteVeiculoById_success() throws Exception {
	        Mockito.when(veiculoService.consultarVeiculoPeloID(RECORD_2.getId())).thenReturn(RECORD_2);

	        mockMvc.perform(MockMvcRequestBuilders
	                        .delete("/veiculos/210")
	                        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

}
