package com.example.CRUDManagement.service;

import static org.junit.Assert.*;

import com.example.CRUDManagement.model.ModulEmployee;
import com.example.CRUDManagement.repo.EmployeeRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllData(){
        List<ModulEmployee> ListEmployee = new ArrayList<ModulEmployee>();
        ListEmployee.add(new ModulEmployee(1L, "bambang", "backend developer"));
        ListEmployee.add(new ModulEmployee(2L, "agus", "backend developer"));
        when(employeeRepo.findAll()).thenReturn(ListEmployee);

        List<ModulEmployee> hasil = employeeService.getAllEmployees();
        assertEquals(2, hasil.size());

    }

    @Test
    public  void testGetEmployeeById (){
        ModulEmployee ListEmployee = new ModulEmployee(1L,"backend developer","bambang");
//        ListEmployee.setId(1L);
//        ListEmployee.setDevisi("backend developer");
//        ListEmployee.setName("bambang");
        when(employeeRepo.findById(1L)).thenReturn(Optional.of(ListEmployee));
        Optional<ModulEmployee> hasil = employeeService.getEmployeeById(1L);
        ModulEmployee hassilData = hasil.get();
         int dataString = hassilData.getId().intValue();
         Long l = 1L;
        assertEquals(l,hassilData.getId());
        assertEquals("bambang", hassilData.getDevisi());
        assertEquals("backend developer", hassilData.getName());

    }

    @Test
    public  void  testAddEmployee(){
        ModulEmployee ListEmployee = new ModulEmployee(1L,"backend developer","test");
        when(employeeRepo.save(ListEmployee)).thenReturn(ListEmployee);
        ModulEmployee data = employeeService.addEmployee(ListEmployee);
        Long l = 1L;
        assertEquals(l,data.getId());
        assertEquals("test", data.getDevisi());
        assertEquals("backend developer", data.getName());

    }

    @Test
    public  void testDeleteEmployee(){
        ModulEmployee ListEmployee = new ModulEmployee(1L,"backend developer","test");
         employeeService.deleteEmployeeById(ListEmployee.getId());
         verify(employeeRepo).deleteById(1L);
    }

}