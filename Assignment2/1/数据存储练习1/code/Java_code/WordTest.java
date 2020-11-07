import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;

//测试单词还原状态
public class WordTest
{
    public static void main(String[] args)
    {
        Properties props = new Properties();
        // 设置相应的properties
        props.put("annotators", "tokenize,ssplit,pos,parse,depparse");
        props.put("tokenize.options", "ptb3Escaping=false");
        props.put("parse.maxlen", "10000");
        props.put("depparse.extradependencies", "SUBJ_ONLY");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props); // 获得StanfordCoreNLP 对象

        String str = "i 've had the player for about 2 years now and it still performs nicely with the exception of an occasional wwhhhrrr sound from the motor .";
        Annotation document = new Annotation(str);
        pipeline.annotate(document);
        CoreMap sentence = document.get(CoreAnnotations.SentencesAnnotation.class).get(0);
        SemanticGraph dependency_graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);  // 获得依赖关系图
        System.out.println("\n\nDependency Graph: " + dependency_graph.toString(SemanticGraph.OutputFormat.LIST));
    }// 直接打印关系
}

