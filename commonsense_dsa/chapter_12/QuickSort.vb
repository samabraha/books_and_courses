Public Class QuckcSort
    Private Sub quicksort()
        Dim aiData(7) As Integer
        aiData(0) = 6
        aiData(1) = 5
        aiData(2) = 1
        aiData(3) = 3
        aiData(4) = 8
        aiData(5) = 2
        aiData(6) = 7
        aiData(7) = 4

        Dim stdOut as String
        stdOut = "Original List" & vbNewLine & vbNewLine
        For i As Integer = 0 To 7
            stdOut = stdOut & aiData(i) & "   "
        Next

        stdOut = stdOut & vbNewLine  & vbNewLine & "Partitioned List"  & vbNewLine  & vbNewLine
        Call Partition(aiData, 0, 7)

        For i As Integer = 0 To 7
            stdOut = stdOut & aiData(i) & "   "
        Next
        MsgBox(stdOut)

    End Sub

    Function Partition(ByRef aiData As Integer(), iLeft As Integer, iRight As Integer)
        Dim iPivot As Integer
        iPivot = aiData(iLeft)
        Dim stCurrentPointer As String

        stCurrentPointer = "Right"
        Do While iLeft <> iRight
            If stCurrentPointer = "Right" Then
                If aiData(iRight) < iPivot Then
                    aiData(iLeft) = aiData(iRight)
                    ' aiData(iRight) = aiData(iPivot)
                    iLeft = iLeft + 1
                    stCurrentPointer = "Left"
                Else
                    iRight = iRight - 1
                End If
            ElseIf stCurrentPointer = "Left" Then
                if aiData(iLeft) > iPivot Then
                    aiData(iRight) = aiData(iLeft)
                    iRight = iRight - 1
                    stCurrentPointer = "Right"
                Else
                    iLeft = iLeft + 1
                End If
            End If
        Loop
        aiData(iLeft) = iPivot
        Return iLeft
    End Function

    Function Partition2(ByRef aiData As Integer(), iLeft As Integer, iRight As Integer)
        Dim iPivot As Integer
        iLeft = iLeft
        iPivot = aiData(iLeft)

        Do While iLeft <> iRight
            Do While (aiData(iRight) > iPivot) And (iLeft <> iRight)
                iRight = iRight - 1
            Loop

            aiData(iLeft) = aiData(iRight)

            Do While
                iLeft = iLeft + 1
            Loop

            aiData(iRight) = aiData(iLeft)
        Loop

        aiData(iLeft) = iPivot
        Return iLeft
    End Function
End Class
