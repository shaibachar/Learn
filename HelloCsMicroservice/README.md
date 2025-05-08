
## add tests
dotnet new xunit -n HelloCs.Tests
dotnet sln add HelloCs.Tests/HelloCs.Tests.csproj
dotnet add HelloCs.Tests reference HelloCs/HelloCs.csproj

dotnet add package Moq


# build test and run 
dotnet clean
dotnet build
dotnet test