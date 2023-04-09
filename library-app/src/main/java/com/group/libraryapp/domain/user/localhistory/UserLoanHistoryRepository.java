package com.group.libraryapp.domain.user.localhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    //select * from user_loan_history where book_name =?, is_return = ?;
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);

    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);
}
