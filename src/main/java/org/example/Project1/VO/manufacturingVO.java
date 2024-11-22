package org.example.Project1.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class manufacturingVO {
    private String manufacturer_code; // 제조업체 코드
    private String manufacturer_name; // 제조업체명
    private String sorting; // 업종
    private int license_number; // 제조자등록번호
}