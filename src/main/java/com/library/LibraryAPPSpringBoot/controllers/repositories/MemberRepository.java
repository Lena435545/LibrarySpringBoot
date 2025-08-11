package com.library.LibraryAPPSpringBoot.controllers.repositories;

import com.library.LibraryAPPSpringBoot.controllers.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
