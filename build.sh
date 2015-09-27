#!/bin/bash
export ANT_HOME=/usr/share/ant
export JAVA_HOME=/usr/lib/jvm/default-java
export CLASSPATH=
export PATH={JAVA_HOME}/bin:${ANT_HOME}/bin:$PATH
export TOMCAT_LIB=/usr/share/tomcat7/lib
ant -version
java -version

export SBSHOME=${SBSHOME:-`pwd`}
sdasdas
cd $SBSHOME || exit

if [ "$1" = "clean" ]; then
   rm -rf docs/WEB-INF/classes/*
   exit
fi
[ ! -d docs/WEB-INF/classes ] && mkdir -p docs/WEB-INF/classes
parm1="${1}"
if [ "${1}" != "${parm1#*java}" ]; then
   echo "(${*})"
   export INDIV_FILE="$$"
   mkdir -p /tmp/i_b_$INDIV_FILE
   cp ${*} /tmp/i_b_$INDIV_FILE
   ant INDIVIDUAL
   rm -rf /tmp/i_b_$INDIV_FILE
elif [ "${1}" != "${parm1%*.jsp}" ]; then
   echo "(${*})"
   export JSPSRC="${*}"
   export JAVASRC=$(echo "${JSPSRC}" | sed -e 's|docs/|_|g' | sed -e 's|/|/_|g' | sed -e 's|\.|_|g' | sed -e 's|_jsp|_jsp.java|g')
   if [ "${JAVASRC}" = "${JAVASRC#_}" ]; then
     JAVASRC="_${JAVASRC}"
   fi
   ant compile_jsp_file
else
   ant ${1}
fi

