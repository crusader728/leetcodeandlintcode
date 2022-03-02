package withJava.crusader728.leetcode.hashmap;

import java.util.*;

public class DesignInMemoryFileSystem588 {
    static class FileSystem {
        static public interface FileSystemNodeVisitor<T> {
            T forDirectory(Directory directory);
            T forPlainTextFile(PlainTextFile file);
        }

        static public abstract class FileSystemNode {
            public abstract <T> T accept(FileSystemNodeVisitor<T> visitor);
        }

        static public class Directory extends FileSystemNode {
            private TreeMap<String, FileSystemNode> children;

            public Directory() {
                this.children = new TreeMap<>();
            }

            public void addChild(String name, FileSystemNode child) {
                this.children.put(name, child);
            }

            @Override
            public <T> T accept(FileSystemNodeVisitor<T> visitor) {
                return visitor.forDirectory(this);
            }


        }

        static public class PlainTextFile extends FileSystemNode {
            private String content;

            public PlainTextFile() {
                this.content = null;
            }

            public void appendContent(String value) {
                if(this.content == null) {
                    this.content = value;
                } else {
                    this.content = this.content + value;
                }
            }

            @Override
            public <T> T accept(FileSystemNodeVisitor<T> visitor) {
                return visitor.forPlainTextFile(this);
            }
        }


        public static final class DirectoryWalker implements FileSystemNodeVisitor<List<String>> {
            private int idx;
            private String[] parts;
            
            public DirectoryWalker(String path) {
                parts = path.split("/");
                idx = 0;
                while(idx < parts.length && parts[idx].isEmpty()) {
                    idx++;
                }
            }

            @Override
            public List<String> forDirectory(Directory directory) {
                if(idx == parts.length) {
                    List<String> result = new ArrayList<>();
                    for(String child: directory.children.keySet()) {
                        result.add(child);
                    }
                    return result;
                } else {
                    FileSystemNode node = directory.children.get(parts[idx]);
                    this.idx++;
                    return node.accept(this);
                }
            }

            @Override
            public List<String> forPlainTextFile(PlainTextFile file) {
                if(idx == parts.length) {
                    return Collections.singletonList(parts[idx - 1]);
                } else {
                    throw new IllegalStateException();
                }
            }

        }


        public static final class MkDir implements FileSystemNodeVisitor<Void> {
            private int idx;
            private String[] parts;

            public MkDir(String path) {
                parts = path.split("/");
                idx = 0;
                while(idx < parts.length && parts[idx].isEmpty()) {
                    idx++;
                }
            }

            @Override
            public Void forDirectory(Directory directory) {
                if(idx == parts.length) {
                    return null;
                } else {
                    String sub = parts[idx++];
                    directory.children.putIfAbsent(sub, new Directory());
                    return directory.children.get(sub).accept(this);
                }
            }

            @Override
            public Void forPlainTextFile(PlainTextFile file) {
                throw new IllegalStateException();
            }
        }

        public static final class AppendContent implements FileSystemNodeVisitor<Void> {
            private String[] parts;
            private int idx;
            private String content;

            public AppendContent(String path, String content) {
                parts = path.split("/");
                idx = 0;
                while(idx < parts.length && parts[idx].isEmpty()) {
                    idx++;
                }
                this.content = content;
            }
            

            @Override
            public Void forDirectory(Directory directory) {
                if(idx >= parts.length) {
                    throw new IllegalStateException();
                } else if(idx != parts.length - 1) {
                    String sub = parts[idx++];
                    directory.children.putIfAbsent(sub, new Directory());
                    return directory.children.get(sub).accept(this);
                } else {
                    String sub = parts[idx++];
                    directory.children.putIfAbsent(sub, new PlainTextFile());
                    return directory.children.get(sub).accept(this);
                }
            }

            @Override
            public Void forPlainTextFile(PlainTextFile file) {
                if(idx != parts.length) {
                    throw new IllegalStateException();
                }

                file.appendContent(this.content);
                return null;
            }
        }

        public static class ContentReader implements FileSystemNodeVisitor<String> {
            private String[] parts;
            private int idx;

            public ContentReader(String path) {
                parts = path.split("/");
                idx = 0;
                while(idx < parts.length && parts[idx].isEmpty()) {
                    idx++;
                }
            }

            @Override
            public String forDirectory(Directory directory) {
                if(idx == parts.length) {
                    throw new IllegalStateException();
                }

                return directory.children.get(parts[idx++]).accept(this);
            }

            @Override
            public String forPlainTextFile(PlainTextFile file) {
                if(idx != parts.length) {
                    throw new IllegalStateException();
                }
                return file.content;
            }

        }



        private FileSystemNode root;

        public FileSystem() {
            root = new Directory();    
        }
        
        public List<String> ls(String path) {
            DirectoryWalker directoryWalker = new DirectoryWalker(path);
            return root.accept(directoryWalker);
        }
        
        public void mkdir(String path) {
            root.accept(new MkDir(path));
        }
        
        public void addContentToFile(String filePath, String content) {
            root.accept(new AppendContent(filePath, content));
        }
        
        public String readContentFromFile(String filePath) {
            return root.accept(new ContentReader(filePath));
        }
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/goowmfn");
        System.out.println(String.join(",", fileSystem.ls("/goowmfn")));
        System.out.println(String.join(",", fileSystem.ls("/")));
        fileSystem.mkdir("/z");
        System.out.println(String.join(",", fileSystem.ls("/")));
        fileSystem.addContentToFile("/goowmfn/c", "shetopcy");
        System.out.println(String.join(",", fileSystem.ls("/goowmfn/c")));
    }
}
