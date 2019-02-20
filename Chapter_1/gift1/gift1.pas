Program gif1;

Var
	outFile, inFile : Text;
	names : array[0..10] of String;
	totals : array[0..10] of Integer;
	i, j, np, npeople : Integer;

Begin
	assign(inFile, 'ride.in');
	assign(outFile, 'ride.out');
	reset(inFile);
	readln(inFile,np);
	for i := 0  to np do
    begin
        readln(inFile, names[npeople++]);
    end;
	for i := 0  to np do
    begin
        
    end;
	close(inFile);
	rewrite(outFile);
    writeln(outFile, 'STAY')
	close(outFile);
End.
