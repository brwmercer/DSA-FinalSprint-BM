package org.example.bst.repository;

import org.example.bst.model.TreeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeEntryRepository extends JpaRepository<TreeEntry, Long> {
}
