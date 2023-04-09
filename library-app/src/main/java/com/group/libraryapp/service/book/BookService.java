package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.localhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.localhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;
    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){
        /*
        1. 책 정보 가져오기
        2. 대출기록 정보 확인해서 대출 중인지 확인하기
        3. 만약에 대출 중이라면 예외를 발생시킴
        4. 유저 정보를 가져오기
        5. 유저 정보와 책 정보를 기반으로 UserLoanHistory를 저장
         */
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("이미 대출중인 책입니다.");
        }

        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        history.doReturn();
//        userLoanHistoryRepository.save(history); //변경 감지하므로 없어도 됨
    }
}
