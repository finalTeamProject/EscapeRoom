package com.noexit.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AdminDashboard {

	private long totalCafeCount;    // 전체 카페 수
    private long totalMemberCount;  // 전체 회원 수
    private long totalThemeCount;   // 전체 테마 수
	
	
}
