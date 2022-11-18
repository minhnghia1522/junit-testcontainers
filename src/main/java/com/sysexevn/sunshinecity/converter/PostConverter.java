package com.sysexevn.sunshinecity.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;

@Mapper(componentModel = "spring")
public interface PostConverter {

	PostConverter MAPPER = Mappers.getMapper(PostConverter.class);

	Post convertToDomain(PostDTO dto);

	PostDTO convertToDTO(Post post);
	
	List<PostDTO> convertToListDTO(List<Post> posts);
}
