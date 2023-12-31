package com.kkori.kkori.member.service.impl;

import com.kkori.kkori.error.entity.CustomException;
import com.kkori.kkori.error.entity.ErrorCode;
import com.kkori.kkori.member.dto.req.MemberUpdateRequest;
import com.kkori.kkori.member.dto.res.MemberResponse;
import com.kkori.kkori.member.entity.Member;
import com.kkori.kkori.member.repository.MemberRepository;
import com.kkori.kkori.member.service.dto.MemberUpdateDto;
import com.kkori.kkori.member.service.dto.RegisterMemberDeviceDto;
import com.kkori.kkori.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    private final S3Service s3Service;

    @Transactional
    public MemberUpdateDto updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));
        findMember.getMemberInfo().updateMemberInfo(memberUpdateRequest);

        return new MemberUpdateDto(findMember);
    }

    public void deleteRefreshToken(Long id) {
        Member findMember = getMember(id);
        findMember.deleteRefreshToken();
    }

    @Transactional
    public RegisterMemberDeviceDto registerDevice(Long memberId, RegisterMemberDeviceDto registerMemberDeviceDto) {
        Member member = getMember(memberId);
        member.registerDevice(registerMemberDeviceDto.getDeviceNumber());
        Member save = memberRepository.save(member);
        return new RegisterMemberDeviceDto(save);
    }

    public MemberResponse findMember(Long memberId) {
        return new MemberResponse(getMember(memberId));
    }

    private Member getMember(Long id) {
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없음"));
        return findMember;
    }


    public String updateProfileImg(MultipartFile profileImg, Long loginId) {
        Member findMember = memberRepository.findById(loginId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        String imageUrl = s3Service.uploadFile(profileImg);

        findMember.getMemberInfo().updateProfileImg(imageUrl);

        memberRepository.save(findMember);

        return findMember.getEmail();
    }

    //    @Override
//    public String removeProfileImg(Long loginId) {
//        Member findMember = memberRepository.findById(loginId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
//        if(findMember.getMemberInfo().getProfileImg() != null){
//            fileManger.removeImgFile(findMember.getMemberInfo().getProfileImg(), storage);
////            removeImgFile(findMember.getMemberInfo().getProfileImg());
//        }
//        findMember.getMemberInfo().updateProfileImg(null);
//        return findMember.getEmail();
//    }
    public String delete(Long loginId) {
        Member findMember = memberRepository.findById(loginId).orElseThrow(() -> new IllegalArgumentException("멤버를 찾을 수 없음"));
        findMember.getMemberInfo().updateDeleted(1);

        return findMember.getEmail();
    }


}
