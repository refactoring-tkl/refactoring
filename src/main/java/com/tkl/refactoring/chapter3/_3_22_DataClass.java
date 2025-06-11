package com.tkl.refactoring.chapter3;

import java.util.List;

import lombok.Data;
import lombok.Getter;

public class _3_22_DataClass {
}

@Data
class Member {
	int memberId;
	String name;
	String email;
	String phoneNumber;
	Team team;
}

@Data
class Team {
	int teamId;
	String teamName;
	List<Member> members;
}

class service {
	void addMember(Member member, Team team) {
		team.getMembers().add(member);
		member.setTeam(team);
	}
}

/**
 * ===== after refactoring =====
 */

@Getter
class MemberAfter {
	int memberId;
	String name;
	String email;
	String phoneNumber;
	TeamAfter team;

	public void setTeam(TeamAfter team) {
		this.team = team;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@Getter
class TeamAfter {
	int teamId;
	String teamName;
	List<MemberAfter> members;

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	void addMember(MemberAfter member) {
		members.add(member);
		member.setTeam(this);
	}
}
class ServiceAfter {
	public static void main(String[] args) {
		MemberAfter member = new MemberAfter();
		member.setTeam(new TeamAfter());
		member.setMemberId(1);
		member.setName("member1");

		TeamAfter team = new TeamAfter();
		team.setTeamId(1);
		team.setTeamName("team1");
		team.addMember(member);
	}
}