import { Theme } from '@mui/material';

export const getStyles = (theme: Theme) => {
  return {
    outerBox: {
      marginTop: '82px',
    },
    card: {
      minWidth: 275,
      backgroundColor: theme.palette.mode === 'dark' ? theme.palette.grey[900] : 'whitesmoke',
    },
    subtitleTypography: {
      color: theme.palette.primary.main,
    },
    h5Typography: {
      color: theme.palette.primary.main,
      marginBottom: 2,
    },
    body2Typography: {
      color: theme.palette.primary.main,
    },
    innerBox: {
      position: 'relative',
    },
    textField: {
      color: theme.palette.mode === 'dark' ? theme.palette.primary.main : 'inherit',
      '& .MuiOutlinedInput-input': {
        color: theme.palette.mode === 'dark' ? theme.palette.primary.main : 'inherit',
      },
    },
    sendButton: {
      position: 'absolute',
      bottom: 0,
      right: 0,
      margin: '0 10px 10px 0',
      color: theme.palette.primary.main,
    },
    commentBox: {
      display: 'flex',
      alignItems: 'center',
      marginTop: 1,
      marginBottom: 1,
    },
    commentButton: {
      color: theme.palette.mode === 'dark' ? theme.palette.primary.main : theme.palette.primary.main,
      paddingLeft: 1,
    },
    commentTypography: {
      color: theme.palette.primary.main,
      marginLeft: 1,
    },
    messageButton: {
      marginLeft: 'auto',
      color: theme.palette.primary.main,
    },
    deleteButton: {
      marginLeft: 'auto',
      color: theme.palette.primary.main,
    },
    commentSectionBox: {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'flex-start',
      paddingLeft: 2,
    },
    commentInnerBox: {
      border: '1px solid',
      borderColor: theme.palette.primary.main,
      borderRadius: '4px',
      marginTop: 1,
      marginBottom: 1,
      width: '75%',
      position: 'relative',
    },
    editTextField: {
      color: theme.palette.mode === 'dark' ? theme.palette.primary.main : 'inherit',
      '& .MuiOutlinedInput-input': {
        color: theme.palette.mode === 'dark' ? theme.palette.primary.main : 'inherit',
      },
    },
    commentBodyTypography: {
      color: theme.palette.primary.main,
      padding: 1,
    },
    deleteCommentButton: {
      position: 'absolute',
      right: 2,
      bottom: 2,
      color: theme.palette.primary.main,
    },
    editButton: {
      position: 'absolute',
      right: 40,
      bottom: 2,
      color: theme.palette.primary.main,
    },
    confirmEditButton: {
      position: 'absolute',
      right: 70,
      bottom: 2,
      color: theme.palette.primary.main,
    },
  };
};
