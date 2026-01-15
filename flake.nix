{
  description = "Hytale plugin development environment";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs = { self, nixpkgs }:
    let
      systems = [ "x86_64-linux" "aarch64-linux" ];
      forAllSystems = nixpkgs.lib.genAttrs systems;
    in
    {
      devShells = forAllSystems (system:
        let
          pkgs = nixpkgs.legacyPackages.${system};
          jdk = pkgs.javaPackages.compiler.temurin-bin.jdk-25;
        in
        {
          default = pkgs.mkShell {
            name = "hytale-dev";

            buildInputs = [
              jdk
              pkgs.maven
            ];

            shellHook = ''
              export JAVA_HOME="${jdk}"
              echo "Hytale plugin development environment"
              echo "Java version: $(java --version | head -n 1)"
              echo "Maven version: $(mvn --version | head -n 1)"
            '';
          };
        });
    };
}
