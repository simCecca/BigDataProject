package bigdata.project1.job3;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import bigdata.project1.job2.AnalisysJob2;
import bigdata.project1.job2.Avarage;
import bigdata.project1.job2.CombinerJob2;
import bigdata.project1.job2.MapperJob2;
import bigdata.project1.job2.MapperPart2;
import bigdata.project1.job2.ReducerJob2;
import bigdata.project1.job2.ReducerPart2;
import bigdata.project1.job2.YearProductWritable;

public class AnalisysJob3 {
	
	public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException
	{
		Job job = new Job(new Configuration(), "AmazonJob2");

		job.setJarByClass(AnalisysJob3.class);
		job.setMapperClass(MapperJob3.class);
		job.setCombinerClass(CombinerJob3.class);
		job.setReducerClass(ReducerJob3.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapOutputKeyClass(YearProductWritable.class);
		job.setMapOutputValueClass(Avarage.class);

		job.setOutputKeyClass(YearProductWritable.class);
		job.setOutputValueClass(DoubleWritable.class);

		job.waitForCompletion(true);


		Job job2 = new Job(new Configuration(), "AmazonJob2.2");
		job2.setJarByClass(AnalisysJob2.class);
		job2.setMapperClass(MapperPart2.class);
		job2.setReducerClass(ReducerPart2.class);

		FileInputFormat.addInputPath(job2, new Path(args[1]));
		FileOutputFormat.setOutputPath(job2, new Path(args[1] + "/final"));

		job2.setMapOutputKeyClass(Text.class);
		job2.setMapOutputValueClass(Text.class);

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

		job2.waitForCompletion(true);

	}

}
