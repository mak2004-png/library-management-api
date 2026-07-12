package library_management.service;

import library_management.exception.DuplicateEmailException;
import library_management.exception.ResourceNotFoundException;
import library_management.model.Member;
import library_management.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {

        return memberRepository.findById(id).
                orElseThrow(() -> {
                    return new ResourceNotFoundException("Member id " + id + " not found");
                });
    }

    public Member addMember(Member member){
        Optional<Member> existingMember = memberRepository.findByEmail(member.getEmail());
        if (existingMember.isPresent()){
            throw new DuplicateEmailException("Email "+ member.getEmail() +" already exists" );
        }
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member memberDetails){
        Member member = memberRepository.findById(id).
                orElseThrow(()->{
                    return new ResourceNotFoundException("Member id "+ id +" not found");
                });
        member.setName(memberDetails.getName());
        member.setEmail(memberDetails.getEmail());
        return memberRepository.save(member);
    }

    public void deleteMember(Long id){
        boolean exists = memberRepository.existsById(id);
        if(!exists){
            throw new ResourceNotFoundException("Member id "+ id +" not found");
        }
        memberRepository.deleteById(id);

    }
}

