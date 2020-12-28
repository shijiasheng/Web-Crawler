package edu.tongji.nep4jbackend.controller;

import edu.tongji.nep4jbackend.entities.ActorEntity;
import edu.tongji.nep4jbackend.repository.ActorRepository;
import edu.tongji.nep4jbackend.repository.DirectorRepository;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    ActorRepository aRepository;


    @GetMapping("/getCoActList")
    public void getCoActList()
    {
        ArrayList<ArrayList<String>> actorC=new ArrayList<>();
        //得到所有演员名
        ArrayList<String> actorList=aRepository.getAllActor();

        for (int i = 0; i < actorList.size(); i++) {
            //得到所有和当前演员合作的演员列表
            List<String> coActorList=aRepository.queryCoActMovie(actorList.get(i));


            for (int j = 0; j < coActorList.size(); j++) {
                String tem1=actorList.get(i);
                String tem2=coActorList.get(j);
                Integer temI=actorList.indexOf(tem2);
                if (tem1.equals(tem2)||(temI!=-1&&temI<i))
                {
                    continue;
                }
                ArrayList<String> tem=new ArrayList<>();
                tem.add(tem1);
                tem.add(tem2);
                actorC.add(tem);
            }
        }
        List<Integer> coTimes=new ArrayList<>();
        for (int i = 0; i < actorC.size(); i++) {
            coTimes.add(aRepository.queryCoActMovieCount(actorC.get(i).get(0),actorC.get(i).get(1)));
        }
        int a=1;
    }
}