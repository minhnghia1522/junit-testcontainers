package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.domain.Post;
import com.sysexevn.sunshinecity.dto.PostDTO;

@Mapper(componentModel = "spring")
public interface PostConverter {

	PostConverter MAPPER = Mappers.getMapper(PostConverter.class);

	Post convertToDomain(PostDTO dto);

	PostDTO convertToDTO(Post post);
	
	List<PostDTO> convertToListDTO(List<Post> posts);
}
