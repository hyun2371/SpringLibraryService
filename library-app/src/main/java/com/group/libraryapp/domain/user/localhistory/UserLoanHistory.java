package com.group.libraryapp.domain.user.localhistory;

import com.group.libraryapp.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    private String bookName;

    private boolean isReturn;


    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    //반납
    public void doReturn(){
        this.isReturn = true;
    }
}
