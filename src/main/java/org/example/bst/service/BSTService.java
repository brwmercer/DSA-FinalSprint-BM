package org.example.bst.service;
import org.example.bst.model.TreeEntry;
import org.example.bst.repository.TreeEntryRepository;
import org.example.bst.BSTBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BSTService {

    private final TreeEntryRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    public BSTService(TreeEntryRepository repository) {
        this.repository = repository;
    }

    public TreeEntry processNumbers(String numbers, boolean b) throws Exception {
        List<Integer> numberList = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        BSTBuilder builder = new BSTBuilder();
        BSTBuilder.Node root = builder.buildTree(numberList);
        Map<String, Object> treeJsonMap = builder.toJson(root);
        String jsonString = mapper.writeValueAsString(treeJsonMap);

        TreeEntry entry = new TreeEntry(numbers, jsonString);
        return repository.save(entry);
    }

    public List<TreeEntry> getAllEntries() {
        return repository.findAll();
    }
}