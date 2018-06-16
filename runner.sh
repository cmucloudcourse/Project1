#!/bin/bash

################################################################################
##                       Cloud Computing Course                               ##
##                    Runner Script for Project 1                             ##
##                                                                            ##
##            Copyright 2018-2019: Cloud Computing Course                     ##
##                     Carnegie Mellon University                             ##
##   Unauthorized distribution of copyrighted material, including             ##
##  unauthorized peer-to-peer file sharing, may subject the students          ##
##  to the fullest extent of Carnegie Mellon University copyright policies.   ##
################################################################################

################################################################################
##                      README Before You Start                               ##
################################################################################
# Fill in the functions below for each question.
# You may use any programming language(s) in any question.
# You may use other files or scripts in these functions as long as they are in
# the submission folder.
# All files MUST include source code (e.g. do not just submit jar or pyc files).
#
# We will suggest tools or libraries in each question to enrich your learning.
# You are allowed to solve questions without the recommended tools or libraries.
#
# The colon `:` is a POSIX built-in basically equivalent to the `true` command,
# REPLACE it with your own command in each function.
# Before you fill your solution,
# DO NOT remove the colon or the function will break because the bash functions
# may not be empty!


################################################################################
##                          Setup & Cleanup                                   ##
################################################################################

setup() {
  # Fill in this helper function to do any setup if you need to.
  #
  # This function will be executed once at the beginning of the grading process.
  # Other functions may be executed repeatedly in arbitrary order.
  # Make use of this function to reduce unnecessary overhead and to make your
  # code behave consistently.
  #
  # e.g. You should compile Java code in this function.
  #
  # However, DO NOT use this function to solve any question or
  # generate any intermediate output.
  #
  # Examples:
  # mvn clean package
  # pip3 install -r requirements.txt
  #
  # Standard output format:
  # No standard output needed
  mvn clean package
}

cleanup() {
  # Fill this helper function to clean up if you need to.
  #
  # This function will be executed once at the end of the grading process
  # Other functions might be executed repeatedly in arbitrary order.
  #
  # Examples:
  # mvn clean
  #
  # Standard output format:
  # No standard output needed
  mvn clean
}

################################################################################
##                        Data Pre-processing                                 ##
################################################################################

filter() {
  # (45 points)
  #
  # Question:
  # Fill this function to filter the input stream read from the dataset `pageviews-20161109-000000.gz`
  # and redirect the output stream to a file called `output`.
  # You need to read the gz file directly as the input stream, instead of
  # uncompressing and then reading it.
  # You need to redirect the standard output stream to a file named `output`,
  # instead of using File I/O.
  #
  # One possible approach is to use `zcat` to uncompress the gz file to StdOut
  # and pipe it to your data filter program.
  #
  # Examples:
  # zcat pageviews-20161109-000000.gz | python data_filter.py
  # zcat pageviews-20161109-000000.gz | java -cp project1.jar edu.cmu.scs.cc.project1.DataFilter
  #
  # Hint for Java users:
  # DO NOT compile java code in this function,
  # you should put the `mvn` or `javac` command in the setup() function
  #
  # Standard output format:
  # No standard output needed
  #
  # Note: the program must be encoding aware
  gunzip -c /Users/Manas/Cloud_course/aws/project1/Project1/pageviews-20161109-000000.gz | java -cp ./target/project1.jar edu.cmu.scs.cc.project1.DataFilter > output

}

################################################################################
##                              MapReduce                                     ##
################################################################################

# Hint:
# As we demonstrated in the video "Writing a Streaming Program in Java for
# Hadoop Streaming" and the section "Test locally with a Small Dataset", your
# mapper and reducer should execute correctly with the following piping process.
#
# # map phase
# for f in $*; do
#   export mapreduce_map_input_file=$f
#   zcat $f | ./runner.sh -r map >> mapout.$$
# done
# # reduce phase
# LC_ALL=C sort -k1,1i mapout.$$ | ./runner.sh -r reduce >> mapred_output
#
# Fill the following functions to your mapper and reducer which accept input
# from StdIn and provide output through Stdout.

map() {
  # Fill this function to run your mapper.
  #
  # Examples:
  # python mapper.py
  # java -cp project1.jar edu.cmu.scs.cc.project1.Mapper
  #
  # Hint for Java users:
  # DO NOT compile java code in this function, you should put the `javac` or
  # `mvn` command in the setup() function instead
  #
  # Standard input format:
  # `domain_code page_title count_views total_response_size`
  # Standard output format:
  # Tab-separated key-value pairs based on your design
  #
  # Note: the program must be encoding aware
  :
}

reduce() {
  # Fill this function to run your reducer.
  #
  # Examples:
  # python reducer.py
  # java -cp project1.jar edu.cmu.scs.cc.project1.Reducer
  #
  # Hint for Java users:
  # DO NOT compile java code in this function, you should put the `javac` or
  # `mvn` command in the setup() function instead
  #
  # Standard input format:
  # Tab-separated key-value pairs generated by your mapper
  # Standard output format:
  # monthly_view  page_title daily_view ... daily_view (i.e. in TSV format)
  #
  # Note: the program must be encoding aware
  :
}

################################################################################
##                           Data Analysis                                    ##
################################################################################
# In the following sections, you should write a function to compute the answer
# and print it to StdOut for each question.
#
# Finish the related primers first if you have not done so, as they will help
# you to get well prepared for the questions.
#
# WARNING:
# Treat every function as an independent and standalone program.
# Any question below should not depend on the result or the intermediate
# output from another.

q1() {
  # (5 points) read a TSV file to a DataFrame and calculate descriptive
  # statistics
  #
  # Question:
  # Print column-wise descriptive statistics of the output file.
  #
  # 1. the statistics metrics required are as follows (in a tabular view):
  #       monthly_view  daily_view  daily_view ...  daily_view
  # count     ...           ...         ...             ...
  # mean      ...           ...         ...             ...
  # std       ...           ...         ...             ...
  # min       ...           ...         ...             ...
  # 25%       ...           ...         ...             ...
  # 50%       ...           ...         ...             ...
  # 75%       ...           ...         ...             ...
  # max       ...           ...         ...             ...
  # 2. the statistics result should be in CSV format
  # 3. format each value to 2 decimal places
  #
  # A DataFrame represents a tabular, spreadsheet-like data structure containing
  # an ordered collection of columns.
  # As you notice, there is an intuitive relation between DataFrame and TSV
  # files.
  #
  # Use the pandas lib to read a TSV file and generate descriptive statistics
  # without having to reinvent the wheel.
  #
  # Hints:
  # 1. pandas.read_table can read a TSV file and return a DataFrame
  # 2. be careful that you should not read the first record as the header
  # 3. figure out the method of pandas.DataFrame which can calculate
  # descriptive statistics
  # 4. figure out the parameter of pandas.DataFrame.to_csv to format the
  # floating point numbers.
  #
  # You can solve the problem by filling in the correct method or parameters
  # to the following Python code:
  #
  # <import libs on your own>
  # df = pandas.read_table('output', index_col=1, encoding='utf-8', <params>)
  # df.<method>.to_csv(sys.stdout, encoding='utf-8', <params>)
  #
  # `index_col=1` means the second column of the input file, i.e. `page_title`
  # will be read as indexes.
  #
  # Standard output format:
  # count,6130.00,6130.00,...,6130.00
  # mean,235884.75,6356.04,...,7256.01
  # std,433129.27,13291.35,...,19867.40
  # min,100005.00,0.00,...,22.00
  # 25%,118625.25,3089.00,...,3395.25
  # 50%,151206.50,4221.00,...,4616.50
  # 75%,228864.00,6332.75,...,6835.00
  # max,17838479.00,438908.00,...,1169461.00
  :
}

q2() {
  # (Honors task) introduction to Series
  #
  # Question:
  # Print the 95th percentile of daily pageviews on Nov 9.
  #
  # The other basic Data Structure in pandas is Series.
  #
  # 1. a DataFrame can be thought of as a dictionary of Series:
  # each column is a Series and all the Series share the same index.
  # 2. Series is a one-dimensional array-like object containing an array of data
  # and an associated array of data labels, called its index.
  #
  # The workflow to solve this question can be as follows:
  # 1. read the file as a DataFrame
  # 2. select the column of Nov 9
  # 3. calculate the 95th percentile
  #
  # In this question, you will explore one of the approaches to get a column
  # from a DataFrame: integer position based selection (iloc).
  #
  # Read the "Selection By Position" section at:
  # https://pandas.pydata.org/pandas-docs/stable/indexing.html
  #
  # You can solve the problem by filling in the correct positions or parameters
  # to the following Python code:
  #
  # <import libs on your own>
  # df = pandas.read_table('output', index_col=1, encoding='utf-8', <params>)
  # print("%.2f" % df.iloc[<positions>].<method>)
  #
  # Standard output format:
  # <a decimal value with 2 decimal places>
  :
}

q3() {
  # (Honors task) the essence of a Series is a dictionary
  #
  # Question:
  # Print the first 5 records in the output file along with monthly pageviews
  # as a JSON object.
  # The keys are the articles and the values are the pageviews.
  #
  # A series can be thought of a dictionary/map in essence.
  # A dictionary/map can be represented as a JSON object, which is an unordered
  # set of name/value pairs.
  #
  # Hint:
  # 1. use pandas.DataFrame.iloc[pos] to select the monthly_view column as a
  # Series
  # 2. the naming of the method which converts a pandas.Series to JSON is
  # self-explanatory
  # 3. to select the first 5 records, you can choose one of the following
  # approaches:
  # 3.1. use a method of pandas.Series similar to GNU tool `head` which
  # returns the first n rows
  # 3.2. use pandas.Series.iloc[pos]
  #
  # Standard output format:
  # {"Donald_Trump":17838479,"AMGTV":10521834,"Proyecto_40":9321993,
  # "United_States_presidential_election,_2016":9016683,"Melania_Trump":6443631}
  :
}

q4() {
  # (Honors task) rank DataFrame and break ties
  #
  # Question:
  # `senators.txt` has a list of articles about United States senators and
  # senators-elect. Please rank the senators and senators-elect by the
  # monthly pageviews. Print the ranked result as a JSON object.
  #
  # The keys of the JSON object are the articles, and the values are the
  # rankings (not pageviews).
  #
  # If the name of a senator or senator-elect does not exist in `output`, do
  # not include it in the ranking.
  #
  # Use "dense ranking" to deal with ties, a.k.a "1223" ranking.
  # If A ranks ahead of B and C (which compare equal) and both ranked ahead of
  # D, then A gets ranking number 1 ("first"), B gets ranking number 2
  # ("joint second"), C also gets ranking number 2 ("joint second") and D gets
  # ranking number 3 ("third").
  # Reference: https://en.wikipedia.org/wiki/Ranking
  #
  # There is no need to (re)implement your own ranking algorithm.
  # pandas.DataFrame.rank enables users to choose from different ways to
  # assign a rank to tied elements.
  #
  # One possible solution is as follows:
  #
  # <import libs on your own>
  # monthly_view = pandas.read_table('output', index_col=1, encoding='utf-8', \
  # <params>).iloc[<positions>]
  # pandas.read_table('senators.txt', index_col=0, encoding='utf-8').join( \
  # monthly_view, how='inner')
  # print(monthly_view.rank(<params>).<methods>.to_json())
  #
  # Standard output format:
  # {"Barbara_Boxer":16,"Chuck_Schumer":10,...,"Rand_Paul":17,"Ted_Cruz":9}
  :
}

################################################################################
##                    DO NOT MODIFY ANYTHING BELOW                            ##
################################################################################
declare -ar questions=("filter" "q1" "q2" "q3" "q4")
declare -ar mapred=("map" "reduce")

readonly usage="This program is used to execute your solution.
Usage:
./runner.sh to run all the questions
./runner.sh -r <question_id> to run one single question
./runner.sh -s to run setup() function
./runner.sh -c to run cleanup() function
Example:
./runner.sh -r q1 to run q1"

contains() {
  local e
  for e in "${@:2}"; do [[ "$e" == "$1" ]] && return 0; done
  return 1
}

while getopts ":hr:sc" opt; do
  case ${opt} in
    h)
      echo "$usage" >&2
      exit
    ;;
    s)
      setup
      echo "setup() function executed" >&2
      exit
    ;;
    c)
      cleanup
      echo "cleanup() function executed" >&2
      exit
    ;;
    r)
      question=$OPTARG
      if contains "$question" "${questions[@]}"; then
        answer=$("$question")
        echo -n "$answer"
      else
        if contains "$question" "${mapred[@]}"; then
          "$question"
        else
          echo "Invalid question id" >&2
          echo "$usage" >&2
          exit 2
        fi
      fi
      exit
    ;;
    \?)
      echo "Invalid option: -$OPTARG"  >&2
      echo "$usage" >&2
      exit 2
    ;;
  esac
done

if [ -z "$1" ]; then
  setup 1>&2
  echo "setup() function executed" >&2
  echo "The answers generated by executing your solution are: " >&2

  for question in "${questions[@]}"; do
    echo "$question:"
    result="$("$question")"
    if [[ "$result" ]]; then
      echo "$result"
    else
      echo ""
    fi
  done
  cleanup 1>&2
  echo "cleanup() function executed" >&2
  echo "If you feel these values are correct please run:"  >&2
  echo "./submitter"  >&2
else
  echo "Invalid usage" >&2
  echo "$usage" >&2
  exit 2
fi
