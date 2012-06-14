# Build script for salsa.
# These commands build the Salsa distribution using the javac debug info flag.

echo "IOS Build Script v0.1"
echo "Please make sure the current directory is in your CLASSPATH"
echo "and that salsa is installed and in your CLASSPATH as well."
echo ""

echo "Compling IOS Package"
echo "salsa source..."
java salsac.SalsaCompiler `find src/ | grep "salsa$"`
echo "java source..."
javac `find src/ | grep "java$"`

echo ""
echo "Finished!"
