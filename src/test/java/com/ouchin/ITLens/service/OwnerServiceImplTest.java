package com.ouchin.ITLens.service;
import com.ouchin.ITLens.Mappers.OwnerMapper;
import com.ouchin.ITLens.dto.owner.OwnerRequestDto;
import com.ouchin.ITLens.dto.owner.OwnerResponseDto;
import com.ouchin.ITLens.entity.Owner;
import com.ouchin.ITLens.repository.OwnerRepository;
import com.ouchin.ITLens.service.impl.OwnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases for findAll()
    @Test
    void testFindAll_WhenOwnersExist() {
        Owner owner = new Owner();
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();

        when(ownerRepository.findAll()).thenReturn(List.of(owner));
        when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDto);

        List<OwnerResponseDto> result = ownerService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(ownerRepository, times(1)).findAll();  // Correct order of arguments in verify
    }

    @Test
    void testFindAll_WhenNoOwnersExist() {
        when(ownerRepository.findAll()).thenReturn(List.of());

        List<OwnerResponseDto> result = ownerService.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(ownerRepository, times(1)).findAll();  // Correct order of arguments in verify
    }

    // Test cases for findById()
    @Test
    void testFindById_WhenOwnerExists() {
        Long id = 1L;
        Owner owner = new Owner();
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();

        when(ownerRepository.findById(id)).thenReturn(Optional.of(owner));
        when(ownerMapper.toDto(owner)).thenReturn(ownerResponseDto);

        OwnerResponseDto result = ownerService.findById(id);

        assertNotNull(result);
        verify(ownerRepository, times(1)).findById(id);  // Correct order of arguments in verify
    }

    @Test
    void testFindById_WhenOwnerDoesNotExist() {
        Long id = 1L;

        when(ownerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ownerService.findById(id));

        assertEquals("Owner not found", exception.getMessage());
        verify(ownerRepository, times(1)).findById(id);  // Correct order of arguments in verify
    }

    // Test cases for create()
    @Test
    void testCreate_WhenValidRequest() {
        OwnerRequestDto requestDto = new OwnerRequestDto();
        Owner owner = new Owner();
        Owner savedOwner = new Owner();
        OwnerResponseDto responseDto = new OwnerResponseDto();

        when(ownerMapper.toEntity(requestDto)).thenReturn(owner);
        when(ownerRepository.save(owner)).thenReturn(savedOwner);
        when(ownerMapper.toDto(savedOwner)).thenReturn(responseDto);

        OwnerResponseDto result = ownerService.create(requestDto);

        assertNotNull(result);
        verify(ownerRepository, times(1)).save(owner);  // Correct order of arguments in verify
        verify(ownerMapper, times(1)).toEntity(requestDto);
    }

    @Test
    void testCreate_WhenMapperFails() {
        OwnerRequestDto requestDto = new OwnerRequestDto();

        when(ownerMapper.toEntity(requestDto)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> ownerService.create(requestDto));
        verify(ownerMapper, times(1)).toEntity(requestDto);
        verify(ownerRepository, never()).save(any());
    }

    // Test cases for update()
    @Test
    void testUpdate_WhenOwnerExists() {
        Long id = 1L;
        OwnerRequestDto requestDto = new OwnerRequestDto();
        Owner owner = new Owner();
        Owner updatedOwner = new Owner();
        OwnerResponseDto responseDto = new OwnerResponseDto();

        when(ownerRepository.findById(id)).thenReturn(Optional.of(owner));
        doNothing().when(ownerMapper).updateOwnerFromDto(requestDto, owner);
        when(ownerRepository.save(owner)).thenReturn(updatedOwner);
        when(ownerMapper.toDto(updatedOwner)).thenReturn(responseDto);

        OwnerResponseDto result = ownerService.update(id, requestDto);

        assertNotNull(result);
        verify(ownerRepository, times(1)).findById(id);  // Correct order of arguments in verify
        verify(ownerMapper, times(1)).updateOwnerFromDto(requestDto, owner);
    }

    @Test
    void testUpdate_WhenOwnerDoesNotExist() {
        Long id = 1L;
        OwnerRequestDto requestDto = new OwnerRequestDto();

        when(ownerRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ownerService.update(id, requestDto));

        assertEquals("Owner not found", exception.getMessage());
        verify(ownerRepository, times(1)).findById(id);  // Correct order of arguments in verify
        verify(ownerRepository, never()).save(any());
    }

    // Test cases for delete()
    @Test
    void testDelete_WhenOwnerExists() {
        Long id = 1L;
        doNothing().when(ownerRepository).deleteById(id);

        assertDoesNotThrow(() -> ownerService.delete(id));
        verify(ownerRepository, times(1)).deleteById(id);  // Correct order of arguments in verify
    }

    @Test
    void testDelete_WhenOwnerDoesNotExist() {
        Long id = 1L;

        doThrow(new RuntimeException("Owner not found")).when(ownerRepository).deleteById(id);

        Exception exception = assertThrows(RuntimeException.class, () -> ownerService.delete(id));

        assertEquals("Owner not found", exception.getMessage());
        verify(ownerRepository, times(1)).deleteById(id);  // Correct order of arguments in verify
    }
}
