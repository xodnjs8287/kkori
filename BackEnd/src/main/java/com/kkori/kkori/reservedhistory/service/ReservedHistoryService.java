package com.kkori.kkori.reservedhistory.service;

import com.kkori.kkori.dog.dto.RegisterDogResponse;
import com.kkori.kkori.dogjobboard.entity.DogJobBoard;
import com.kkori.kkori.dogjobboard.repository.DogJobBoardRepository;
import com.kkori.kkori.jobboard.dto.RegisterJobBoardResponse;
import com.kkori.kkori.jobboard.entity.JobBoard;
import com.kkori.kkori.jobboard.repository.JobBoardRepository;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.reservedhistory.dto.ReservedHistoryResponse;
import com.kkori.kkori.reservedhistory.entity.ReservedHistory;
import com.kkori.kkori.reservedhistory.repository.ReservedHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservedHistoryService {

    private final ReservedHistoryRepository reservedHistoryRepository;

    private final JobBoardRepository jobBoardRepository;

    private final MemberRepository memberRepository;

    private final DogJobBoardRepository dogJobBoardRepository;

    @Transactional
    public ReservedHistoryResponse makeReserved(String sitterEmail, Long memberId, Long jobBoardId) {

        ReservedHistory build = ReservedHistory.builder()
                .isCompleted(false)
                .sitter(getMemberByEmail(sitterEmail))
                .member(getMemberById(memberId))
                .jobBoard(getJobBoard(jobBoardId))
                .build();

        return new ReservedHistoryResponse(reservedHistoryRepository.save(build));

    }

    public List<RegisterJobBoardResponse> findAllByMemberAndSitter(Long sitterId, String deviceNumber){
        Member member = memberRepository.findByDeviceNumber(deviceNumber)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버"));
        Member sitter = memberRepository.findById(sitterId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시터"));

        return reservedHistoryRepository.findAllByMemberAndSitter(member, sitter)
                .stream()
                .filter(it-> it.getIsCompleted().equals(Boolean.FALSE))
                .map(reservedHistory -> {
                    JobBoard jobBoard = reservedHistory.getJobBoard();
                    List<DogJobBoard> dogJobBoards = dogJobBoardRepository.findAllByJobBoard(jobBoard);
                    List<RegisterDogResponse> dogResponses = dogJobBoards.stream()
                            .map(DogJobBoard::getDog)
                            .map(dog -> new RegisterDogResponse(dog))
                            .collect(Collectors.toList());

                    RegisterJobBoardResponse response = new RegisterJobBoardResponse(jobBoard);
                    response.setDogs(dogResponses);
                    response.setEmail(member.getEmail());
                    response.setNickName(member.getMemberInfo().getNickName());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<ReservedHistoryResponse> findAllReservedHistory(Long memberId){

        Member member = getMemberById(memberId);
        return reservedHistoryRepository.findAllByMember(member)
                .stream()
                .map(ReservedHistoryResponse::new)
                .collect(Collectors.toList());

    }

    public List<RegisterJobBoardResponse> findAllReservedHistoryBySitter(Long sitterId){
        Member member = getMemberById(sitterId);

         return reservedHistoryRepository.findAllBySitter(member)
                .stream()
                .map(ReservedHistory::getJobBoard)
                .map(RegisterJobBoardResponse::new)
                .collect(Collectors.toList());

    }




    @Transactional
    public void makeCompleted(Long reservedHistoryId) {
        ReservedHistory reservedHistory = reservedHistory(reservedHistoryId);
        reservedHistory.makeCompleted();

        reservedHistoryRepository.save(reservedHistory);

    }

    private ReservedHistory reservedHistory(Long reservedHistoryId) {
        return reservedHistoryRepository.findById(reservedHistoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약내역"));
    }

    private JobBoard getJobBoard(Long jobBoardId) {
        return jobBoardRepository.findById(jobBoardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 잡게시판"));
    }

    private Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private Member getMemberByEmail(String sitterEmail) {
        return memberRepository.findByEmail(sitterEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 시터입니다."));
    }
}
