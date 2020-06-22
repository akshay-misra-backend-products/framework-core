export interface DataTableConfig {
	data: any[];
	tableTitle: string;
	columns: any[];
	topToolbar?: any[];
	inlineToolbar?: any[];
	loadChildren?: Function;
	loadAPI?: string;
}
