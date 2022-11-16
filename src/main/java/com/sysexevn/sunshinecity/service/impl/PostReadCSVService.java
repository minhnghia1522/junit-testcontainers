package com.sysexevn.sunshinecity.service.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.sysexevn.sunshinecity.dto.PostDTO;

@Service
public class PostReadCSVService {

	@SuppressWarnings("deprecation")
	public static List<PostDTO> read(String fileName) throws IOException {

		List<PostDTO> list = new ArrayList<>();
		CSVParser csvParser = new CSVParser(new FileReader(fileName),
				CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build());
		for (CSVRecord record : csvParser) {
			PostDTO dto = new PostDTO();
			dto.setTitle(record.get(1));
			dto.setPostName(record.get(2));
			dto.setPostDescription(record.get(3));
			dto.setCreatedAt(new Date(record.get(4)));
			dto.setUpdatedAt(new Date(record.get(5)));
			dto.setPostTypeId(Integer.parseInt(record.get(6)));
			dto.setAcceptedAnswerId(Integer.parseInt(record.get(7)));
			dto.setParentId(Integer.parseInt(record.get(8)));
			dto.setScore(Integer.parseInt(record.get(9)));
			dto.setViewCount(Integer.parseInt(record.get(10)));
			dto.setBody(record.get(11));
			dto.setOwnerUserId(Integer.parseInt(record.get(12)));
			dto.setOwnerDisplayName(record.get(13));
			dto.setTags(record.get(14));
			dto.setAnswerCount(Integer.parseInt(record.get(15)));
			dto.setCommentCount(Integer.parseInt(record.get(16)));
			dto.setFavoriteCount(Integer.parseInt(record.get(17)));
			dto.setClosedDate(new Date(record.get(18)));
			dto.setCommunityOwnerDate(new Date(record.get(19)));
			list.add(dto);
		}
		csvParser.close();
		return list;
	}
}
