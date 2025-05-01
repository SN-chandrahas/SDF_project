# import subprocess
# import os
# import sys

# class MyInfArith :
#     def __init__(self,mode):
#         self.file_path="C:/Projects/SDF_project_CS58/src/main/java/"
#         self.mode=mode

#     def compile(self):
#         os.system(f"javac -cp .:{self.file_path}target/aarithmetic.jar {self.file_path}arbitraryarithmetic/MyInfArith.java")
    
#     def run(self,operation, num1, num2):
#         os.system(f"java -cp .:{self.file_path}target/aarithmetic.jar {self.file_path}myinfarith.MyInfArith {self.mode} {operation} {num1} {num2}")

# def main():
#     import sys

# def main():
#     if len(sys.argv) != 5:
#         print("Usage: python script.py <mode> <operation> <num1> <num2>")
#         return

#     mode = sys.argv[1]
#     operation = sys.argv[2]
#     num1 = sys.argv[3]
#     num2 = sys.argv[4]

#     app = MyInfArith(mode)
#     app.compile()
#     app.run(operation, num1, num2)

# if __name__ == "__main__":
#     main()
import subprocess
import sys
import os

class MyInfArith:
    def __init__(self, mode):
        self.mode = mode
        self.java_src_path = "C:/Projects/SDF_project_CS58/src/main/java"
        self.classpath = "C:/Projects/SDF_project_CS58/target/classes"
        self.full_classname = "arbitraryarithmetic.MyInfArith" 

    def compile(self):
        compile_cmd = [
            "javac",
            "-d", self.classpath,
            "-cp", self.classpath,
            os.path.join(self.java_src_path, "arbitraryarithmetic", "MyInfArith.java")
        ]
        subprocess.run(compile_cmd, check=True)

    def run(self, operation, num1, num2):
        run_cmd = [
            "java",
            "-cp", self.classpath,
            self.full_classname,
            self.mode, operation, num1, num2
        ]
        subprocess.run(run_cmd, check=True)

def main():
    if len(sys.argv) != 5:
        print("Usage: python MyInfArith"
        ".py <mode> <operation> <num1> <num2>")
        return

    mode = sys.argv[1]
    operation = sys.argv[2]
    num1 = sys.argv[3]
    num2 = sys.argv[4]

    app = MyInfArith(mode)
    #app.compile()
    app.run(operation, num1, num2)

if __name__ == "__main__":
    main()


