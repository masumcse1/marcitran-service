package com.ufril.medtran.persistence.service.impl;

import com.ufril.medtran.persistence.domain.common.Tag;
import com.ufril.medtran.persistence.repository.common.TagRepository;
import com.ufril.medtran.persistence.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(long id) {
        return tagRepository.findOne(id);
    }

    @Override
    @Transactional
    public long createTag(Tag tag) {
        Tag data = tagRepository.save(tag);
        return data.getId();
    }

    @Override
    @Transactional
    public long upDateTag(Tag tag) {
        Tag data = tagRepository.save(tag);
        return data.getId();
    }
}
