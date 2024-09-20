package com.ufril.medtran.persistence.service;

import com.ufril.medtran.persistence.domain.common.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag getTagById(long id);
    long createTag(Tag tag);
    long upDateTag(Tag tag);
}
