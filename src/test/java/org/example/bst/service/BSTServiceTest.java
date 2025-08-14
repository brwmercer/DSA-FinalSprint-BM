package org.example.bst.service;

import org.example.bst.model.TreeEntry;
import org.example.bst.repository.TreeEntryRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BSTServiceTest {

    @Test
    void processNumbersParsesBuildsAndSaves() throws Exception {
        TreeEntryRepository repo = mock(TreeEntryRepository.class);
        when(repo.save(any(TreeEntry.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BSTService svc = new BSTService(repo);
        TreeEntry saved = svc.processNumbers("10,5,15", false);

        assertNotNull(saved);
        assertEquals("10,5,15", saved.getInputNumbers());
        assertNotNull(saved.getTreeJson());
        assertTrue(saved.getTreeJson().contains("\"value\":10"));
        verify(repo, times(1)).save(any(TreeEntry.class));
    }

    @Test
    void getAllEntriesReturnsFromRepo() {
        TreeEntryRepository repo = mock(TreeEntryRepository.class);
        when(repo.findAll()).thenReturn(Collections.emptyList());

        BSTService svc = new BSTService(repo);
        assertEquals(0, svc.getAllEntries().size());
        verify(repo, times(1)).findAll();
    }
}