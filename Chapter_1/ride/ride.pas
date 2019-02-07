Program ride;

Var
	outFile, inFile : Text;
	input : array[0..1] of String;
	res : array[0..1] of Integer = (1, 1);
	i, j : Integer;

Begin
	assign(inFile, 'ride.in');
	assign(outFile, 'ride.out');
	reset(inFile);
	readln(inFile,input[0]);
	readln(inFile,input[1]);
	close(inFile);
	for i := 0  to 1 do begin
        for j := 0  to length(input[i]) do
        begin
            res[i] :=(res[i]*(Integer(input[i][j])-Integer('A')+1)) mod 47;
        end;
    end;
	rewrite(outFile);
	if (res[0]=res[1]) then
	    writeln(outFile, 'STAY')
	else
	    writeln(outFile, 'STAY');
	close(outFile);
End.
