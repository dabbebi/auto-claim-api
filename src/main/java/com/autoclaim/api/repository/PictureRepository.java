package com.autoclaim.api.repository;

import com.autoclaim.api.entity.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {

    Picture findPictureByPublicId(String publicId);
}
