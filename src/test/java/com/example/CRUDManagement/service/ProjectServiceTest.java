package com.example.CRUDManagement.service;
import com.example.CRUDManagement.model.ModulProject;
import com.example.CRUDManagement.repo.ProjectRepo;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProject(){
        List<ModulProject> listPtoject = new ArrayList<ModulProject>();
        listPtoject.add(new ModulProject(1L,"LOS","BRI"));
        listPtoject.add(new ModulProject(1L,"LOS","MANDIRI"));
        when(projectRepo.findAll()).thenReturn(listPtoject);
        List<ModulProject>  projectData = projectService.getAllProjects();
        assertEquals(2,projectData.size());
    }

    @Test
    public void testGetProjectById(){
        ModulProject listPtoject = new ModulProject(1L,"LOS","MANDIRI");
        when(projectRepo.findById(1L)).thenReturn(Optional.of(listPtoject));
        Optional<ModulProject> dataModul = projectService.getProjectById(1L);
        ModulProject dataFix = dataModul.get();
        Long dataLong = 1L;
        assertEquals(dataLong,dataFix.getId());
        assertEquals("LOS",dataFix.getListProject());
        assertEquals("MANDIRI",dataFix.getCompany());
    }

    @Test
    public  void testAddProject(){
        ModulProject listPtoject = new ModulProject(1L,"LOS","MANDIRI");
        when(projectRepo.save(listPtoject)).thenReturn(listPtoject);
        ModulProject fixData = projectService.addProject(listPtoject);
        Long dataLong = 1L;
        assertEquals(dataLong, fixData.getId());
        assertEquals("LOS", fixData.getListProject());
        assertEquals("MANDIRI", fixData.getCompany());
    }
    @Test
    public  void testUpdateProjecty(){
        ModulProject listPtoject = new ModulProject(1L,"LOS","MANDIRI");
        when(projectRepo.save(listPtoject)).thenReturn(listPtoject);
        Optional<ModulProject> dataFix = Optional.of(listPtoject);
        ModulProject listPtoject2 = new ModulProject(2L,"COREBANKKING","HANA BANK");
        dataFix =     projectService.updateProjectById(2L,listPtoject2)  ;
        ModulProject dataHasil = dataFix.get();
        Long dataLong = 2L;
        assertEquals(dataLong,dataHasil.getId());
        assertEquals("COREBANKKING",dataHasil.getListProject());
        assertEquals("HANA BANK",dataHasil.getCompany());
    }

}