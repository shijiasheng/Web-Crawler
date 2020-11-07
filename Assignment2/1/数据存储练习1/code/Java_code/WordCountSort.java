import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class WordCountSort
{

    public static class TextIntWritable implements WritableComparable<TextIntWritable>
    {

        Text word;  //单词
        IntWritable count;  //次数

        public TextIntWritable()
        {
            set(new Text(), new IntWritable());
        }

        public void set(Text word, IntWritable count)
        {
            this.word = word;
            this.count = count;
        }

        @Override
        public void write(DataOutput out) throws IOException
        {
            word.write(out);
            count.write(out);
        }

        @Override
        public void readFields(DataInput in) throws IOException
        {
            word.readFields(in);
            count.readFields(in);
        }

        @Override
        public String toString()
        {
            return word.toString() + " " + count.toString();
        }

        @Override
        public int hashCode()
        {
            return this.word.hashCode() + this.count.hashCode();
        }

        @Override
        public int compareTo(TextIntWritable o)
        {
            int result = -1 * this.count.compareTo(o.count);  //先比较次数
            if (result != 0)
                return result;
            return this.word.compareTo(o.word); //次数相同，则按字典排序
        }
    }

    public static class WordCountMapper extends Mapper<LongWritable, Text, TextIntWritable, NullWritable>
    {

        @Override
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
        {
            TextIntWritable k = new TextIntWritable();
            String[] string = value.toString().split("[\\W]");
            System.out.println(string[0] + "" + string[1]);
            k.set(new Text(string[0]), new IntWritable(Integer.valueOf(string[1])));
            context.write(k, NullWritable.get());
        }
    }

    public static class WordCountReducer extends Reducer<TextIntWritable, NullWritable, TextIntWritable, NullWritable>
    {
        public void reduce(TextIntWritable key, Iterable<NullWritable> value, Context context) throws IOException, InterruptedException
        {
            for (NullWritable v : value)
                context.write(key, v);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
    {
        if (args.length < 2)
        {
            System.out.println("args must be two");
            return;
        }
        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf, "WordCountSort");
        job.setJarByClass(WordCountSort.class);

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setOutputKeyClass(TextIntWritable.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);


    }

}
